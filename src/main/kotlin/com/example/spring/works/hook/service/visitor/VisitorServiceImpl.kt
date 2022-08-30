package com.example.spring.works.hook.service.visitor

import com.example.spring.works.hook.dto.visitor.VisitorDTO
import com.example.spring.works.hook.dto.visitor.request.RequestVisitorDTO
import com.example.spring.works.hook.dto.visitor.response.ResponseVisitorDTO
import com.example.spring.works.hook.repository.MemberRepository
import com.example.spring.works.hook.repository.visitor.VisitorRepository
import com.example.spring.works.hook.util.error.ErrorCode
import com.example.spring.works.hook.util.error.MyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class VisitorServiceImpl(
    @Autowired val visitorRepository: VisitorRepository,
    @Autowired val memberRepository: MemberRepository
) : VisitorService {

    override fun saveVisitor(request: VisitorDTO): VisitorDTO {
        val users = memberRepository.findByIdIsIn(request.userIds)
        if (users.isEmpty()) throw MyException(ErrorCode.NOT_EXIST_MEMBER)
        val saveVisitor = visitorRepository.save(request.toEntity())
        return saveVisitor.toDTO()
    }

    @Transactional
    override fun addVisitorUser(request: RequestVisitorDTO): VisitorDTO {
        memberRepository.findByIdOrNull(request.userId)
            ?: throw MyException(ErrorCode.NOT_EXIST_MEMBER)

        val visitor = visitorRepository.findByIdOrNull(request.visitorId)
            ?: throw MyException(ErrorCode.NOT_EXIST_VISITOR)

        val duplicate = visitorRepository.findByVisitorIdAndUserId(request)
        if (duplicate.isPresent) throw MyException(ErrorCode.EXIST_VISITOR)

        visitor.addUserList(request.userId)
        return visitor.toDTO()
    }

    override fun selectOneVisitorList(request: RequestVisitorDTO): ResponseVisitorDTO {
        return request.visitorId?.let { visitorRepository.findByAllId(it) }
            ?: throw MyException(ErrorCode.NOT_EXIST_VISITOR)
    }

    override fun selectVisitorAllList(): List<ResponseVisitorDTO> {
        return visitorRepository.findByAllList()
            ?: throw MyException(ErrorCode.EXIST_VISITOR)
    }
}
